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


/**
 * Traduire: false
 **/
public class InscriptionGenPage extends InscriptionGenPageGen<ClusterPage> {

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
		c.o("");
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
		c.o("/inscription");
	}

	@Override protected void _pageImageUri(Couverture<String> c) {
			c.o("/png/inscription-999.png");
	}

	@Override protected void _contexteIconeGroupe(Couverture<String> c) {
			c.o("solid");
	}

	@Override protected void _contexteIconeNom(Couverture<String> c) {
			c.o("pencil-square");
	}

	@Override public void initLoinInscriptionGenPage() {
		initInscriptionGenPage();
		super.initLoinMiseEnPage();
	}

	@Override public void htmlScriptsInscriptionGenPage() {
		e("script").a("src", statiqueUrlBase, "/js/frFR/InscriptionPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/BlocPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/EnfantPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/MerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PerePage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/GardienPage.js").f().g("script");
		e("script").a("src", statiqueUrlBase, "/js/frFR/PaiementPage.js").f().g("script");
	}

	@Override public void htmlScriptInscriptionGenPage() {
		l("$(document).ready(function() {");
		tl(1, "suggereInscriptionScolaireBlocCles($('#formInscriptionScolaireBlocCles'), $('#listInscriptionScolaireBlocCles')); ");
		tl(1, "suggereInscriptionScolaireEnfantCle($('#formInscriptionScolaireEnfantCle'), $('#listInscriptionScolaireEnfantCle')); ");
		tl(1, "suggereInscriptionScolaireMereCles($('#formInscriptionScolaireMereCles'), $('#listInscriptionScolaireMereCles')); ");
		tl(1, "suggereInscriptionScolairePereCles($('#formInscriptionScolairePereCles'), $('#listInscriptionScolairePereCles')); ");
		tl(1, "suggereInscriptionScolaireGardienCles($('#formInscriptionScolaireGardienCles'), $('#listInscriptionScolaireGardienCles')); ");
		tl(1, "suggereInscriptionScolairePaiementCles($('#formInscriptionScolairePaiementCles'), $('#listInscriptionScolairePaiementCles')); ");
		l("});");
	}

	public void htmlFormPageInscriptionScolaire(InscriptionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("class", "").f().sx("ID").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strInscriptionId()).g("span");
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
					{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocHeureDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocHeureDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							LocalTime val = o.getBlocHeureDebut();

							{ e("div").a("class", "w3-cell-row w3-purple ").f();
								e("label").a("for", "Page_blocHeureDebut").a("class", "").f().sx("heure début").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "text")
										.a("class", "w3-input w3-border timepicker ")
										.a("placeholder", "HH:MM AM")
										.a("id", "Page_blocHeureDebut")
										.a("onclick", "enleverLueur($(this)); ")
										.a("value", val == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("fr-FR")).format(val))
										.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $(\"#InscriptionScolaireForm :input[name='pk']\").val() }], 'setBlocHeureDebut', s, function() { ajouterLueur($('#Page_blocHeureDebut')); }, function() { ajouterErreur($('#Page_blocHeureDebut')); }); } ")
										.fg();
								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#Page_blocHeureDebut')); $('#Page_blocHeureDebut').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=\"pk\"]').val() }], 'setBlocHeureDebut', null, function() { ajouterLueur($('#Page_blocHeureDebut')); }, function() { ajouterErreur($('#Page_blocHeureDebut')); }); ")
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
					{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocHeureFin").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocHeureFin").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							LocalTime val = o.getBlocHeureFin();

							{ e("div").a("class", "w3-cell-row w3-purple ").f();
								e("label").a("for", "Page_blocHeureFin").a("class", "").f().sx("heure fin").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									e("input")
										.a("type", "text")
										.a("class", "w3-input w3-border timepicker ")
										.a("placeholder", "HH:MM AM")
										.a("id", "Page_blocHeureFin")
										.a("onclick", "enleverLueur($(this)); ")
										.a("value", val == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("fr-FR")).format(val))
										.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $(\"#InscriptionScolaireForm :input[name='pk']\").val() }], 'setBlocHeureFin', s, function() { ajouterLueur($('#Page_blocHeureFin')); }, function() { ajouterErreur($('#Page_blocHeureFin')); }); } ")
										.fg();
								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#Page_blocHeureFin')); $('#Page_blocHeureFin').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=\"pk\"]').val() }], 'setBlocHeureFin', null, function() { ajouterLueur($('#Page_blocHeureFin')); }, function() { ajouterErreur($('#Page_blocHeureFin')); }); ")
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
					{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocPrixParMois").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocPrixParMois").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-purple ").f();
								e("label").a("for", "Page_blocPrixParMois").a("class", "").f().sx("prix par mois").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "text")
										.a("placeholder", "prix par mois")
										.a("class", "setBlocPrixParMois w3-input w3-border ")
										.a("name", "setBlocPrixParMois")
										.a("id", "Page_blocPrixParMois")
										.a("onclick", "enleverLueur($(this)); ")
										.a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=\"pk\"]').val() }], 'setBlocPrixParMois', $(this).val(), function() { ajouterLueur($('#Page_blocPrixParMois')); }, function() { ajouterErreur($('#Page_blocPrixParMois')); }); ")
										.a("value", o.strBlocPrixParMois())
									.fg();

								} g("div");
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("button")
										.a("tabindex", "-1")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-bar-item w3-purple ")
									.a("onclick", "enleverLueur($('#Page_blocPrixParMois')); $('#Page_blocPrixParMois').val(null); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=\"pk\"]').val() }], 'setBlocPrixParMois', null, function() { ajouterLueur($('#Page_blocPrixParMois')); }, function() { ajouterErreur($('#Page_blocPrixParMois')); }); ")
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
					{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurInscriptionCles")
							.a("class", "valeurInscriptionCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
									e("i").a("class", "fad fa-bell-o w3-padding-small ").f().g("i");
									sx("blocs");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier  a cette inscription");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "blocs")
											.a("title", "La clé primaire des blocs dans la base de données. ")
											.a("class", "valeurObjetSuggere suggereBlocCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setBlocCles")
											.a("id", "Page_blocCles")
											.a("autocomplete", "off")
											.a("oninput", "suggereInscriptionScolaireBlocCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireBlocCles'), $('#listInscriptionScolaireBlocCles')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireBlocCles").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
											.a("onclick", "postBlocScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Page_blocCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_blocCles')); }); ")
											.f().sx("ajouter un bloc")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formInscriptionScolaireEnfantCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurInscriptionCles")
							.a("class", "valeurInscriptionCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireEnfantCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
									e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
									sx("enfants");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier un enfant a cette inscription");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "enfants")
											.a("title", "La clé primaire des enfants dans la base de données. ")
											.a("class", "valeurObjetSuggere suggereEnfantCle w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setEnfantCle")
											.a("id", "Page_enfantCle")
											.a("autocomplete", "off")
											.a("oninput", "suggereInscriptionScolaireEnfantCle($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireEnfantCle'), $('#listInscriptionScolaireEnfantCle')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireEnfantCle").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
											.a("onclick", "postEnfantScolaireVals({ inscriptionCles: \"", o.getPk(), "\" }, function() { var $e = $('#Page_enfantCle'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_enfantCle')); }); ")
											.f().sx("ajouter un enfant")
										.g("button");
									} g("div");
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
					{ e("form").a("action", "").a("id", "formInscriptionScolaireMereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurInscriptionCles")
							.a("class", "valeurInscriptionCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireMereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
									e("i").a("class", "far fa-female w3-padding-small ").f().g("i");
									sx("mères");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier  a cette inscription");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "mères")
											.a("title", "La clé primaire des mères dans la base de données. ")
											.a("class", "valeurObjetSuggere suggereMereCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setMereCles")
											.a("id", "Page_mereCles")
											.a("autocomplete", "off")
											.a("oninput", "suggereInscriptionScolaireMereCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireMereCles'), $('#listInscriptionScolaireMereCles')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireMereCles").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
											.a("onclick", "postMereScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Page_mereCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_mereCles')); }); ")
											.f().sx("ajouter une mère")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formInscriptionScolairePereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurInscriptionCles")
							.a("class", "valeurInscriptionCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolairePereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-light-blue w3-hover-light-blue ").f();
									e("i").a("class", "far fa-male w3-padding-small ").f().g("i");
									sx("pères");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier  a cette inscription");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "pères")
											.a("title", "La clé primaire des pères dans la base de données. ")
											.a("class", "valeurObjetSuggere suggerePereCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setPereCles")
											.a("id", "Page_pereCles")
											.a("autocomplete", "off")
											.a("oninput", "suggereInscriptionScolairePereCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolairePereCles'), $('#listInscriptionScolairePereCles')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePereCles").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
											.a("onclick", "postPereScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Page_pereCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_pereCles')); }); ")
											.f().sx("ajouter un père")
										.g("button");
									} g("div");
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
					{ e("form").a("action", "").a("id", "formInscriptionScolaireGardienCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurInscriptionCles")
							.a("class", "valeurInscriptionCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireGardienCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
									e("i").a("class", "far fa-chevron-right w3-padding-small ").f().g("i");
									sx("gardiens");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier  a cette inscription");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "gardiens")
											.a("title", "La clé primaire des gardiens dans la base de données. ")
											.a("class", "valeurObjetSuggere suggereGardienCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setGardienCles")
											.a("id", "Page_gardienCles")
											.a("autocomplete", "off")
											.a("oninput", "suggereInscriptionScolaireGardienCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireGardienCles'), $('#listInscriptionScolaireGardienCles')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireGardienCles").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
											.a("onclick", "postGardienScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Page_gardienCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_gardienCles')); }); ")
											.f().sx("ajouter un gardien")
										.g("button");
									} g("div");
								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
				{ e("div").a("class", "w3-padding ").f();
					{ e("form").a("action", "").a("id", "formInscriptionScolairePaiementCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeurInscriptionCles")
							.a("class", "valeurInscriptionCles ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolairePaiementCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
									e("i").a("class", "fas fa-dollar w3-padding-small ").f().g("i");
									sx("paiements");
								} g("a");
							} g("div");
							{ e("div").a("class", "w3-cell-row ").f();
								{ e("h5").a("class", "w3-cell ").f();
									sx("relier  a cette inscription");
								} g("h5");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();
									{ e("div").a("class", "w3-cell-row ").f();

									e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
										e("input")
											.a("type", "text")
											.a("placeholder", "paiements")
											.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
											.a("class", "valeurObjetSuggere suggerePaiementCles w3-input w3-border w3-cell w3-cell-middle ")
											.a("name", "setPaiementCles")
											.a("id", "Page_paiementCles")
											.a("autocomplete", "off")
											.a("oninput", "suggereInscriptionScolairePaiementCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolairePaiementCles'), $('#listInscriptionScolairePaiementCles')); ")
										.fg();

									} g("div");
								} g("div");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
									{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePaiementCles").f();
									} g("ul");
									{ e("div").a("class", "w3-cell-row ").f();
										e("button")
											.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
											.a("onclick", "postPaiementScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Page_paiementCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Page_paiementCles')); }); ")
											.f().sx("ajouter un paiement")
										.g("button");
									} g("div");
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
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("class", "").f().sx("famille").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row  ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-rest ").f();
									e("span").f().sx(o.strFamilleCle()).g("span");
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
					{ e("form").a("action", "").a("id", "formInscriptionScolaireInscriptionApprouve").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireInscriptionApprouve").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-purple ").f();
								e("label").a("for", "Page_inscriptionApprouve").a("class", "").f().sx("approuvé").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setInscriptionApprouve")
										.a("name", "setInscriptionApprouve")
										.a("id", "Page_inscriptionApprouve")
										.a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=\"pk\"]').val() }], 'setInscriptionApprouve', $(this).prop('checked'), function() { ajouterLueur($('#Page_inscriptionApprouve')); }, function() { ajouterErreur($('#Page_inscriptionApprouve')); }); ")
										;
										if(o.getInscriptionApprouve() != null && o.getInscriptionApprouve())
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
					{ e("form").a("action", "").a("id", "formInscriptionScolaireInscriptionImmunisations").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "hidden")
							.a("name", "valeur")
							.a("class", "valeur ")
							.a("value", requeteSite_.getRequetePk())
							.fg();
					} g("form");
					{ e("form").a("action", "").a("id", "suggereInscriptionScolaireInscriptionImmunisations").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						{ e("div").a("class", "w3-card ").f();
							{ e("div").a("class", "w3-cell-row w3-purple ").f();
								e("label").a("for", "Page_inscriptionImmunisations").a("class", "").f().sx("vacciné").g("label");
							} g("div");
							{ e("div").a("class", "w3-cell-row w3-padding ").f();
								{ e("div").a("class", "w3-cell ").f();

									e("input")
										.a("type", "checkbox")
										.a("value", "true")
										.a("class", "setInscriptionImmunisations")
										.a("name", "setInscriptionImmunisations")
										.a("id", "Page_inscriptionImmunisations")
										.a("onchange", "patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $('#InscriptionScolaireForm :input[name=\"pk\"]').val() }], 'setInscriptionImmunisations', $(this).prop('checked'), function() { ajouterLueur($('#Page_inscriptionImmunisations')); }, function() { ajouterErreur($('#Page_inscriptionImmunisations')); }); ")
										;
										if(o.getInscriptionImmunisations() != null && o.getInscriptionImmunisations())
											a("checked", "checked");
									fg();

								} g("div");
							} g("div");
						} g("div");
					} g("form");
				} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPOSTInscriptionScolaire(InscriptionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionId()).g("span");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocHeureDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocHeureDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlocHeureDebut();

						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "POST_blocHeureDebut").a("class", "").f().sx("heure début").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "POST_blocHeureDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $(\"#InscriptionScolaireForm :input[name='pk']\").val() }], 'setBlocHeureDebut', s, function() { ajouterLueur($('#POST_blocHeureDebut')); }, function() { ajouterErreur($('#POST_blocHeureDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocHeureFin").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocHeureFin").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlocHeureFin();

						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "POST_blocHeureFin").a("class", "").f().sx("heure fin").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "POST_blocHeureFin")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $(\"#InscriptionScolaireForm :input[name='pk']\").val() }], 'setBlocHeureFin', s, function() { ajouterLueur($('#POST_blocHeureFin')); }, function() { ajouterErreur($('#POST_blocHeureFin')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocPrixParMois").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocPrixParMois").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "POST_blocPrixParMois").a("class", "").f().sx("prix par mois").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prix par mois")
									.a("class", "valeurBlocPrixParMois w3-input w3-border ")
									.a("name", "blocPrixParMois")
									.a("id", "POST_blocPrixParMois")
									.a("value", o.strBlocPrixParMois())
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
								e("i").a("class", "fad fa-bell-o w3-padding-small ").f().g("i");
								sx("blocs");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "blocs")
										.a("title", "La clé primaire des blocs dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereBlocCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setBlocCles")
										.a("id", "POST_blocCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireBlocCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireBlocCles'), $('#listInscriptionScolaireBlocCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireBlocCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
										.a("onclick", "postBlocScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#POST_blocCles'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_blocCles')); }); ")
										.f().sx("ajouter un bloc")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireEnfantCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireEnfantCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
								sx("enfants");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier un enfant a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "enfants")
										.a("title", "La clé primaire des enfants dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereEnfantCle w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setEnfantCle")
										.a("id", "POST_enfantCle")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireEnfantCle($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireEnfantCle'), $('#listInscriptionScolaireEnfantCle')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireEnfantCle").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postEnfantScolaireVals({ inscriptionCles: \"", o.getPk(), "\" }, function() { var $e = $('#POST_enfantCle'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_enfantCle')); }); ")
										.f().sx("ajouter un enfant")
									.g("button");
								} g("div");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireMereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireMereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-female w3-padding-small ").f().g("i");
								sx("mères");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "mères")
										.a("title", "La clé primaire des mères dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereMereCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setMereCles")
										.a("id", "POST_mereCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireMereCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireMereCles'), $('#listInscriptionScolaireMereCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireMereCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
										.a("onclick", "postMereScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#POST_mereCles'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_mereCles')); }); ")
										.f().sx("ajouter une mère")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolairePereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolairePereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-light-blue w3-hover-light-blue ").f();
								e("i").a("class", "far fa-male w3-padding-small ").f().g("i");
								sx("pères");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "pères")
										.a("title", "La clé primaire des pères dans la base de données. ")
										.a("class", "valeurObjetSuggere suggerePereCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setPereCles")
										.a("id", "POST_pereCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolairePereCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolairePereCles'), $('#listInscriptionScolairePereCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePereCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
										.a("onclick", "postPereScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#POST_pereCles'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_pereCles')); }); ")
										.f().sx("ajouter un père")
									.g("button");
								} g("div");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireGardienCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireGardienCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-chevron-right w3-padding-small ").f().g("i");
								sx("gardiens");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "gardiens")
										.a("title", "La clé primaire des gardiens dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereGardienCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setGardienCles")
										.a("id", "POST_gardienCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireGardienCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireGardienCles'), $('#listInscriptionScolaireGardienCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireGardienCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postGardienScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#POST_gardienCles'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_gardienCles')); }); ")
										.f().sx("ajouter un gardien")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolairePaiementCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolairePaiementCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fas fa-dollar w3-padding-small ").f().g("i");
								sx("paiements");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "paiements")
										.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
										.a("class", "valeurObjetSuggere suggerePaiementCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setPaiementCles")
										.a("id", "POST_paiementCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolairePaiementCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolairePaiementCles'), $('#listInscriptionScolairePaiementCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePaiementCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postPaiementScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#POST_paiementCles'); $e.html($e.val()); }, function() { ajouterErreur($('#POST_paiementCles')); }); ")
										.f().sx("ajouter un paiement")
									.g("button");
								} g("div");
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
						e("label").a("class", "").f().sx("famille").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strFamilleCle()).g("span");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireInscriptionApprouve").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireInscriptionApprouve").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "POST_inscriptionApprouve").a("class", "").f().sx("approuvé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurInscriptionApprouve")
									.a("name", "inscriptionApprouve")
									.a("id", "POST_inscriptionApprouve")
									;
									if(o.getInscriptionApprouve() != null && o.getInscriptionApprouve())
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireInscriptionImmunisations").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireInscriptionImmunisations").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "POST_inscriptionImmunisations").a("class", "").f().sx("vacciné").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurInscriptionImmunisations")
									.a("name", "inscriptionImmunisations")
									.a("id", "POST_inscriptionImmunisations")
									;
									if(o.getInscriptionImmunisations() != null && o.getInscriptionImmunisations())
										a("checked", "checked");
								fg();

							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormPATCHInscriptionScolaire(InscriptionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionId()).g("span");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocHeureDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocHeureDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlocHeureDebut();

						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "PATCH_blocHeureDebut").a("class", "").f().sx("heure début").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "PATCH_blocHeureDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $(\"#InscriptionScolaireForm :input[name='pk']\").val() }], 'setBlocHeureDebut', s, function() { ajouterLueur($('#PATCH_blocHeureDebut')); }, function() { ajouterErreur($('#PATCH_blocHeureDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocHeureFin").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocHeureFin").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlocHeureFin();

						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "PATCH_blocHeureFin").a("class", "").f().sx("heure fin").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "PATCH_blocHeureFin")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $(\"#InscriptionScolaireForm :input[name='pk']\").val() }], 'setBlocHeureFin', s, function() { ajouterLueur($('#PATCH_blocHeureFin')); }, function() { ajouterErreur($('#PATCH_blocHeureFin')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocPrixParMois").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocPrixParMois").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "PATCH_blocPrixParMois").a("class", "").f().sx("prix par mois").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prix par mois")
									.a("class", "setBlocPrixParMois w3-input w3-border ")
									.a("name", "setBlocPrixParMois")
									.a("id", "PATCH_blocPrixParMois")
									.a("value", o.strBlocPrixParMois())
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
								e("i").a("class", "fad fa-bell-o w3-padding-small ").f().g("i");
								sx("blocs");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "blocs")
										.a("title", "La clé primaire des blocs dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereBlocCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setBlocCles")
										.a("id", "PATCH_blocCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireBlocCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireBlocCles'), $('#listInscriptionScolaireBlocCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireBlocCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
										.a("onclick", "postBlocScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#PATCH_blocCles'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_blocCles')); }); ")
										.f().sx("ajouter un bloc")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireEnfantCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireEnfantCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
								sx("enfants");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier un enfant a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "enfants")
										.a("title", "La clé primaire des enfants dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereEnfantCle w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setEnfantCle")
										.a("id", "PATCH_enfantCle")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireEnfantCle($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireEnfantCle'), $('#listInscriptionScolaireEnfantCle')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireEnfantCle").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postEnfantScolaireVals({ inscriptionCles: \"", o.getPk(), "\" }, function() { var $e = $('#PATCH_enfantCle'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_enfantCle')); }); ")
										.f().sx("ajouter un enfant")
									.g("button");
								} g("div");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireMereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireMereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-female w3-padding-small ").f().g("i");
								sx("mères");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "mères")
										.a("title", "La clé primaire des mères dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereMereCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setMereCles")
										.a("id", "PATCH_mereCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireMereCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireMereCles'), $('#listInscriptionScolaireMereCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireMereCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
										.a("onclick", "postMereScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#PATCH_mereCles'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_mereCles')); }); ")
										.f().sx("ajouter une mère")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolairePereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolairePereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-light-blue w3-hover-light-blue ").f();
								e("i").a("class", "far fa-male w3-padding-small ").f().g("i");
								sx("pères");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "pères")
										.a("title", "La clé primaire des pères dans la base de données. ")
										.a("class", "valeurObjetSuggere suggerePereCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setPereCles")
										.a("id", "PATCH_pereCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolairePereCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolairePereCles'), $('#listInscriptionScolairePereCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePereCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
										.a("onclick", "postPereScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#PATCH_pereCles'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_pereCles')); }); ")
										.f().sx("ajouter un père")
									.g("button");
								} g("div");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireGardienCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireGardienCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-chevron-right w3-padding-small ").f().g("i");
								sx("gardiens");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "gardiens")
										.a("title", "La clé primaire des gardiens dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereGardienCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setGardienCles")
										.a("id", "PATCH_gardienCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireGardienCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireGardienCles'), $('#listInscriptionScolaireGardienCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireGardienCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postGardienScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#PATCH_gardienCles'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_gardienCles')); }); ")
										.f().sx("ajouter un gardien")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolairePaiementCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolairePaiementCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fas fa-dollar w3-padding-small ").f().g("i");
								sx("paiements");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "paiements")
										.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
										.a("class", "valeurObjetSuggere suggerePaiementCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setPaiementCles")
										.a("id", "PATCH_paiementCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolairePaiementCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolairePaiementCles'), $('#listInscriptionScolairePaiementCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePaiementCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postPaiementScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#PATCH_paiementCles'); $e.html($e.val()); }, function() { ajouterErreur($('#PATCH_paiementCles')); }); ")
										.f().sx("ajouter un paiement")
									.g("button");
								} g("div");
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
						e("label").a("class", "").f().sx("famille").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strFamilleCle()).g("span");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireInscriptionApprouve").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireInscriptionApprouve").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "PATCH_inscriptionApprouve").a("class", "").f().sx("approuvé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setInscriptionApprouve")
									.a("name", "setInscriptionApprouve")
									.a("id", "PATCH_inscriptionApprouve")
									;
									if(o.getInscriptionApprouve() != null && o.getInscriptionApprouve())
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireInscriptionImmunisations").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireInscriptionImmunisations").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "PATCH_inscriptionImmunisations").a("class", "").f().sx("vacciné").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "setInscriptionImmunisations")
									.a("name", "setInscriptionImmunisations")
									.a("id", "PATCH_inscriptionImmunisations")
									;
									if(o.getInscriptionImmunisations() != null && o.getInscriptionImmunisations())
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionNomComplet()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	public void htmlFormRechercheInscriptionScolaire(InscriptionScolaire o) {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
						e("label").a("class", "").f().sx("ID").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionId()).g("span");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocHeureDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocHeureDebut").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlocHeureDebut();

						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "Recherche_blocHeureDebut").a("class", "").f().sx("heure début").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "Recherche_blocHeureDebut")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $(\"#InscriptionScolaireForm :input[name='pk']\").val() }], 'setBlocHeureDebut', s, function() { ajouterLueur($('#Recherche_blocHeureDebut')); }, function() { ajouterErreur($('#Recherche_blocHeureDebut')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocHeureFin").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocHeureFin").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						LocalTime val = o.getBlocHeureFin();

						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "Recherche_blocHeureFin").a("class", "").f().sx("heure fin").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								e("input")
									.a("type", "text")
									.a("class", "w3-input w3-border timepicker ")
									.a("placeholder", "HH:MM AM")
									.a("id", "Recherche_blocHeureFin")
									.a("onclick", "enleverLueur($(this)); ")
									.a("value", val == null ? "" : DateTimeFormatter.ofPattern("h:mm a", Locale.forLanguageTag("fr-FR")).format(val))
									.a("onchange", "var t = parseTime(this.value); if(t) { var s = dateFormat(t, 'HH MM'); patchInscriptionScolaireVal([{ name: 'fq', value: 'pk:' + $(\"#InscriptionScolaireForm :input[name='pk']\").val() }], 'setBlocHeureFin', s, function() { ajouterLueur($('#Recherche_blocHeureFin')); }, function() { ajouterErreur($('#Recherche_blocHeureFin')); }); } ")
									.fg();
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocPrixParMois").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocPrixParMois").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "Recherche_blocPrixParMois").a("class", "").f().sx("prix par mois").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "text")
									.a("placeholder", "prix par mois")
									.a("class", "valeurBlocPrixParMois w3-input w3-border ")
									.a("name", "blocPrixParMois")
									.a("id", "Recherche_blocPrixParMois")
									.a("value", o.strBlocPrixParMois())
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireBlocCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireBlocCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-indigo w3-hover-indigo ").f();
								e("i").a("class", "fad fa-bell-o w3-padding-small ").f().g("i");
								sx("blocs");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "blocs")
										.a("title", "La clé primaire des blocs dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereBlocCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setBlocCles")
										.a("id", "Recherche_blocCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireBlocCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireBlocCles'), $('#listInscriptionScolaireBlocCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireBlocCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-indigo ")
										.a("onclick", "postBlocScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Recherche_blocCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_blocCles')); }); ")
										.f().sx("ajouter un bloc")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolaireEnfantCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireEnfantCle").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "far fa-child w3-padding-small ").f().g("i");
								sx("enfants");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier un enfant a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "enfants")
										.a("title", "La clé primaire des enfants dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereEnfantCle w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setEnfantCle")
										.a("id", "Recherche_enfantCle")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireEnfantCle($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireEnfantCle'), $('#listInscriptionScolaireEnfantCle')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireEnfantCle").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postEnfantScolaireVals({ inscriptionCles: \"", o.getPk(), "\" }, function() { var $e = $('#Recherche_enfantCle'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_enfantCle')); }); ")
										.f().sx("ajouter un enfant")
									.g("button");
								} g("div");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireMereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireMereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-pink w3-hover-pink ").f();
								e("i").a("class", "far fa-female w3-padding-small ").f().g("i");
								sx("mères");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "mères")
										.a("title", "La clé primaire des mères dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereMereCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setMereCles")
										.a("id", "Recherche_mereCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireMereCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireMereCles'), $('#listInscriptionScolaireMereCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireMereCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-pink ")
										.a("onclick", "postMereScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Recherche_mereCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_mereCles')); }); ")
										.f().sx("ajouter une mère")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolairePereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolairePereCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-light-blue w3-hover-light-blue ").f();
								e("i").a("class", "far fa-male w3-padding-small ").f().g("i");
								sx("pères");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "pères")
										.a("title", "La clé primaire des pères dans la base de données. ")
										.a("class", "valeurObjetSuggere suggerePereCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setPereCles")
										.a("id", "Recherche_pereCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolairePereCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolairePereCles'), $('#listInscriptionScolairePereCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePereCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-light-blue ")
										.a("onclick", "postPereScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Recherche_pereCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_pereCles')); }); ")
										.f().sx("ajouter un père")
									.g("button");
								} g("div");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireGardienCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireGardienCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-yellow w3-hover-yellow ").f();
								e("i").a("class", "far fa-chevron-right w3-padding-small ").f().g("i");
								sx("gardiens");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "gardiens")
										.a("title", "La clé primaire des gardiens dans la base de données. ")
										.a("class", "valeurObjetSuggere suggereGardienCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setGardienCles")
										.a("id", "Recherche_gardienCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolaireGardienCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolaireGardienCles'), $('#listInscriptionScolaireGardienCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolaireGardienCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-yellow ")
										.a("onclick", "postGardienScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Recherche_gardienCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_gardienCles')); }); ")
										.f().sx("ajouter un gardien")
									.g("button");
								} g("div");
							} g("div");
						} g("div");
					} g("div");
				} g("form");
			} g("div");
			} g("div");
			{ e("div").a("class", "w3-cell w3-cell-middle w3-center w3-mobile ").f();
			{ e("div").a("class", "w3-padding ").f();
				{ e("form").a("action", "").a("id", "formInscriptionScolairePaiementCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeurInscriptionCles")
						.a("class", "valeurInscriptionCles ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolairePaiementCles").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("a").a("href", "").a("class", "w3-cell w3-btn w3-center h4 w3-block h4 w3-green w3-hover-green ").f();
								e("i").a("class", "fas fa-dollar w3-padding-small ").f().g("i");
								sx("paiements");
							} g("a");
						} g("div");
						{ e("div").a("class", "w3-cell-row ").f();
							{ e("h5").a("class", "w3-cell ").f();
								sx("relier  a cette inscription");
							} g("h5");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();
								{ e("div").a("class", "w3-cell-row ").f();

								e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
									e("input")
										.a("type", "text")
										.a("placeholder", "paiements")
										.a("title", "La clé primaire des contacts d'urgence dans la base de données. ")
										.a("class", "valeurObjetSuggere suggerePaiementCles w3-input w3-border w3-cell w3-cell-middle ")
										.a("name", "setPaiementCles")
										.a("id", "Recherche_paiementCles")
										.a("autocomplete", "off")
										.a("oninput", "suggereInscriptionScolairePaiementCles($('#' + ($(this).val() ? 'suggere' : 'form') + 'InscriptionScolairePaiementCles'), $('#listInscriptionScolairePaiementCles')); ")
									.fg();

								} g("div");
							} g("div");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
								{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "listInscriptionScolairePaiementCles").f();
								} g("ul");
								{ e("div").a("class", "w3-cell-row ").f();
									e("button")
										.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-green ")
										.a("onclick", "postPaiementScolaireVals({ inscriptionCles: [ \"", o.getPk(), "\" ] }, function() { var $e = $('#Recherche_paiementCles'); $e.html($e.val()); }, function() { ajouterErreur($('#Recherche_paiementCles')); }); ")
										.f().sx("ajouter un paiement")
									.g("button");
								} g("div");
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
					{ e("div").a("class", "w3-cell-row w3-purple ").f();
						e("label").a("class", "").f().sx("famille").g("label");
					} g("div");
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strFamilleCle()).g("span");
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireInscriptionApprouve").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireInscriptionApprouve").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "Recherche_inscriptionApprouve").a("class", "").f().sx("approuvé").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurInscriptionApprouve")
									.a("name", "inscriptionApprouve")
									.a("id", "Recherche_inscriptionApprouve")
									;
									if(o.getInscriptionApprouve() != null && o.getInscriptionApprouve())
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
				{ e("form").a("action", "").a("id", "formInscriptionScolaireInscriptionImmunisations").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					e("input")
						.a("type", "hidden")
						.a("name", "valeur")
						.a("class", "valeur ")
						.a("value", requeteSite_.getRequetePk())
						.fg();
				} g("form");
				{ e("form").a("action", "").a("id", "suggereInscriptionScolaireInscriptionImmunisations").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
					{ e("div").a("class", "w3-card ").f();
						{ e("div").a("class", "w3-cell-row w3-purple ").f();
							e("label").a("for", "Recherche_inscriptionImmunisations").a("class", "").f().sx("vacciné").g("label");
						} g("div");
						{ e("div").a("class", "w3-cell-row w3-padding ").f();
							{ e("div").a("class", "w3-cell ").f();

								e("input")
									.a("type", "checkbox")
									.a("value", "true")
									.a("class", "valeurInscriptionImmunisations")
									.a("name", "inscriptionImmunisations")
									.a("id", "Recherche_inscriptionImmunisations")
									;
									if(o.getInscriptionImmunisations() != null && o.getInscriptionImmunisations())
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
				{ e("div").a("class", "w3-card ").f();
					{ e("div").a("class", "w3-cell-row  ").f();
						{ e("div").a("class", "w3-cell ").f();
							{ e("div").a("class", "w3-rest ").f();
								e("span").f().sx(o.strInscriptionNomComplet()).g("span");
							} g("div");
						} g("div");
					} g("div");
				} g("div");
			} g("div");
			} g("div");
		} g("div");
	}

	@Override public void htmlBodyInscriptionGenPage() {

		OperationRequest operationRequete = requeteSite_.getOperationRequete();
		JsonObject params = operationRequete.getParams();
		if(listeInscriptionScolaire == null || listeInscriptionScolaire.size() == 0) {

			{ e("h1").f();
				{ e("a").a("href", "/inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
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
					{ e("a").a("href", "/inscription").a("class", "w3-bar-item w3-btn w3-center w3-block w3-purple w3-hover-purple ").f();
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
				if(contexteIconeClassesCss != null)
					e("i").a("class", contexteIconeClassesCss + " site-menu-icon ").f().g("i");
				e("span").a("class", " ").f().sx("inscriptions").g("span");
			} g("h1");
			e("div").a("class", "w3-padding-16 w3-card-4 w3-light-grey ").f();
			{ e("table").a("class", "w3-table w3-bordered w3-striped w3-border w3-hoverable ").f();
				{ e("thead").f();
					{ e("tr").f();
						e("th").f().sx("").g("th");
					} g("tr");
				} g("thead");
				{ e("tbody").f();
					Map<String, Map<String, List<String>>> highlighting = listeInscriptionScolaire.getQueryResponse().getHighlighting();
					for(int i = 0; i < listeInscriptionScolaire.size(); i++) {
						InscriptionScolaire o = listeInscriptionScolaire.getList().get(i);
						Map<String, List<String>> highlights = highlighting == null ? null : highlighting.get(o.getId());
						List<String> highlightList = highlights == null ? null : highlights.get(highlights.keySet().stream().findFirst().orElse(null));
						String uri = "/inscription/" + o.getPk();
						{ e("tr").f();
							{ e("td").f();
								{ e("a").a("href", uri).f();
									e("i").a("class", "fas fa-pencil-square w3-padding-small ").f().g("i");
									{ e("span").f();
										sx(o.getInscriptionNomComplet());
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
					} g("form");
					htmlFormPageInscriptionScolaire(o);
				}

			} g("div");

		}
		htmlBodyFormsInscriptionGenPage();
		htmlSuggereInscriptionGenPage();
		g("div");
	}

	public void htmlBodyFormsInscriptionGenPage() {
		e("div").f();


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
			.a("onclick", "$('#postInscriptionScolaireModale').show(); ")
			.f().sx("Créer une inscription")
		.g("button");
		{ e("div").a("id", "postInscriptionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-purple ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#postInscriptionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Créer une inscription").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					InscriptionScolaire o = new InscriptionScolaire();

					// Form POST
					{ e("form").a("action", "").a("id", "postInscriptionScolaireForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPOSTInscriptionScolaire(o);
					} g("form");
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
					e("h2").a("class", "").f().sx("Modifier des inscriptions").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					InscriptionScolaire o = new InscriptionScolaire();

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


		e("button")
			.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
			.a("onclick", "$('#deleteInscriptionScolaireModale').show(); ")
			.f().sx("Supprimer des inscriptions")
		.g("button");
		{ e("div").a("id", "deleteInscriptionScolaireModale").a("class", "w3-modal ").f();
			{ e("div").a("class", "w3-modal-content w3-card-4 ").f();
				{ e("header").a("class", "w3-container w3-purple ").f();
					e("span").a("class", "w3-button w3-display-topright ").a("onclick", "$('#deleteInscriptionScolaireModale').hide(); ").f().sx("×").g("span");
					e("h2").a("class", "").f().sx("Supprimer des inscriptions").g("h2");
				} g("header");
				{ e("div").a("class", "w3-container ").f();
					InscriptionScolaire o = new InscriptionScolaire();

					// Form DELETE
					{ e("form").a("action", "").a("id", "deleteInscriptionScolaireForm").a("onsubmit", "event.preventDefault(); return false; ").f();
						htmlFormPATCHInscriptionScolaire(o);
					} g("form");
					e("button")
						.a("class", "w3-btn w3-round w3-border w3-border-black w3-ripple w3-padding w3-purple ")
						.a("onclick", "deleteInscriptionScolaire(); ")
						.f().sx("Supprimer des inscriptions")
					.g("button");

				} g("div");
			} g("div");
		} g("div");

		g("div");
	}

	/**
	 * Var.enUS: htmlSuggestEnrollmentGenPage
	 * r: "/inscription"
	 * r.enUS: "/enrollment"
	 * r: "voir toutes les inscriptions"
	 * r.enUS: "see all the enrollments"
	 * r: "rechargerInscriptionGenPage"
	 * r.enUS: "refreshEnrollmentGenPage"
	 * r: "recharger toutes les inscriptions"
	 * r.enUS: "refresh all the enrollments"
	 * r: "rechercher inscriptions : "
	 * r.enUS: "search enrollments: "
	 * r: "suggereFormInscriptionScolaire"
	 * r.enUS: "suggestFormSchoolEnrollment"
	 * r: "rechercher inscriptions"
	 * r.enUS: "search enrollments"
	 * r: "suggereInscriptionScolaire w3-input w3-border w3-cell w3-cell-middle "
	 * r.enUS: "suggestSchoolEnrollment w3-input w3-border w3-cell w3-cell-middle "
	 * r: "suggereInscriptionScolaire"
	 * r.enUS: "suggestSchoolEnrollment"
	 * r: patchInscriptionScolaireVals
	 * r.enUS: patchSchoolEnrollmentVals
	 * r: ajouterLueur
	 * r.enUS: addGlow
	 * r: rechargerInscriptionGenPage
	 * r.enUS: refreshEnrollmentGenPage
	 * r: ajouterErreur
	 * r.enUS: addError
	 * r: suggereInscriptionScolaireObjetSuggere
	 * r.enUS: suggestSchoolEnrollmentObjectSuggest
	 * r: 'objetSuggere:'
	 * r.enUS: 'objectSuggest:'
	 * r: '#suggereListInscriptionScolaire'
	 * r.enUS: '#suggestListSchoolEnrollment'
	 * r: "suggereListInscriptionScolaire"
	 * r.enUS: "suggestListSchoolEnrollment"
	**/
	public void htmlSuggereInscriptionGenPage() {
		{ e("div").a("class", "w3-cell-row ").f();
			{ e("div").a("class", "w3-cell ").f();
				{ e("a").a("href", "/inscription").a("class", "").f();
					e("i").a("class", "fas fa-pencil-square w3-padding-small ").f().g("i");
					sx("voir toutes les inscriptions");
				} g("a");
			} g("div");
			{ e("div").a("class", "w3-cell ").f();
				{ e("a").a("id", "rechargerInscriptionGenPage").a("href", "/inscription").a("class", "").a("onclick", "patchInscriptionScolaireVals([], {}, function() { ajouterLueur($('#rechargerInscriptionGenPage')); }, function() { ajouterErreur($('#rechargerInscriptionGenPage')); }); return false; ").f();
					e("i").a("class", "fas fa-sync-alt w3-padding-small ").f().g("i");
					sx("recharger toutes les inscriptions");
				} g("a");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row w3-padding ").f();
			{ e("div").a("class", "w3-cell ").f();
				{ e("span").f();
					sx("rechercher inscriptions : ");
				} g("span");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row w3-padding ").f();
			{ e("div").a("class", "w3-cell ").f();
				{ e("div").a("class", "w3-cell-row ").f();

					e("i").a("class", "far fa-search w3-xxlarge w3-cell w3-cell-middle ").f().g("i");
					{ e("form").a("action", "").a("id", "suggereFormInscriptionScolaire").a("style", "display: inline-block; width: 100%; ").a("onsubmit", "event.preventDefault(); return false; ").f();
						e("input")
							.a("type", "text")
							.a("placeholder", "rechercher inscriptions")
							.a("class", "suggereInscriptionScolaire w3-input w3-border w3-cell w3-cell-middle ")
							.a("name", "suggereInscriptionScolaire")
							.a("id", "suggereInscriptionScolaire")
							.a("autocomplete", "off")
							.a("oninput", "suggereInscriptionScolaireObjetSuggere( [ { 'name': 'q', 'value': 'objetSuggere:' + $(this).val() } ], $('#suggereListInscriptionScolaire')); ")
							.fg();

					} g("form");
				} g("div");
			} g("div");
		} g("div");
		{ e("div").a("class", "w3-cell-row w3-padding ").f();
			{ e("div").a("class", "w3-cell w3-left-align w3-cell-top ").f();
				{ e("ul").a("class", "w3-ul w3-hoverable ").a("id", "suggereListInscriptionScolaire").f();
				} g("ul");
			} g("div");
		} g("div");
	}

}
